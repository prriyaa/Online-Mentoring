package main

import (
        "context"
       "fmt"
      "log"
      "go.mongodb.org/mongo-driver/mongo"
      "go.mongodb.org/mongo-driver/bson"
      "go.mongodb.org/mongo-driver/mongo/options"
        "net/http"
        "github.com/gorilla/mux"
         "encoding/json"
        "io/ioutil"
    )

type Payment struct {
Id int `json:"Id"`
MentorId int `json:"MentorId"`
TrainingId int `json:"TrainingID"`
Txntype string `json:"Txntype"`
Amount string `json:"Amount"`
Datetime string `json:"datetime"`
Remarks string `json:"remarks"`
}


var AllPeople []Payment

func homelink(w http.ResponseWriter,req *http.Request){
fmt.Fprintf(w,"My First Go Rest Api")
}

func AllPayment(w http.ResponseWriter,req *http.Request) {
fmt.Println("sending all payments in response")
clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
    client, err := mongo.Connect(context.TODO(), clientOptions)

    if err != nil {
        log.Fatal(err)
    }

    err = client.Ping(context.TODO(), nil)

    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("Connected to MongoDB!")
collection := client.Database("mydb").Collection("payment")

	findOptions := options.Find()
	//findOptions.SetLimit(2)

   var results []Payment
  cur, err := collection.Find(context.TODO(), bson.D{}, findOptions)
  if err != nil {
      log.Fatal(err)
  }

  for cur.Next(context.TODO()) {
      var elem Payment
      err := cur.Decode(&elem)
      if err != nil {
          log.Fatal(err)
      }

fmt.Printf("Found:", elem)
      results = append(results, elem)
  }

  if err := cur.Err(); err != nil {
      log.Fatal(err)
  }

  cur.Close(context.TODO())
    fmt.Printf("Found multiple documents (array of pointers): %+v\n", results)
json.NewEncoder(w).Encode(results)
}

func GetPayment(w http.ResponseWriter,req *http.Request) {
/*retrieve path parameter
search in array for given id
*/
vars := mux.Vars(req)
idval = vars["id"]

for _,payment:= range AllPeople {
if payment.Id == idval {
json.NewEncoder(w).Encode(payment)
}
}
}

func createPayment(w http.ResponseWriter,req *http.Request){
//fmt.Printf(w,"in createPerson")
reqbody,_:=ioutil.ReadAll(req.Body)
fmt.Printf("reqbody",string(reqbody))
var ppayment Payment
json.Unmarshal(reqbody, &ppayment)
fmt.Println("Recieved Object ",ppayment)
clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
    client, err := mongo.Connect(context.TODO(), clientOptions)

    if err != nil {
        log.Fatal(err)
    }

    err = client.Ping(context.TODO(), nil)

    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("Connected to MongoDB!")
collection := client.Database("mydb").Collection("payment")
    insertResult, err := collection.InsertOne(context.TODO(), ppayment)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("Inserted a Single Document: ", insertResult.InsertedID)
AllPeople=append(AllPeople,ppayment)
fmt.Fprintf(w,"created")
}
func updatePayment(w http.ResponseWriter,req *http.Request) {
    //fmt.Printf(w,"in createPerson")
    vars := mux.Vars(req)
    idval = vars["id"]

    for _, payment := range AllPeople {
        if payment.Id == idval {
            reqbody, _ := ioutil.ReadAll(req.Body)
            fmt.Printf("reqbody", string(reqbody))
            payment.Remarks= "Remarks111"
            json.NewEncoder(w).Encode(payment)
        }

    }
}
func deletePayment(w http.ResponseWriter,req *http.Request) {
    //fmt.Printf(w,"in createPerson")
    vars := mux.Vars(req)
    idval := vars["id"]

    clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
    client, err := mongo.Connect(context.TODO(), clientOptions)

    if err != nil {
        log.Fatal(err)
    }

    err = client.Ping(context.TODO(), nil)

    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("Connected to MongoDB!")

collection := client.Database("mydb").Collection("payment")

     deleteResult, err := collection.DeleteMany(context.TODO(), bson.D{})
  if err != nil {
      log.Fatal(err)
  }

  fmt.Printf("Deleted %v documents in the trainers collection\n", deleteResult.DeletedCount)

  err = client.Disconnect(context.TODO())

  if err != nil {
      log.Fatal(err)
  } else {
      fmt.Println("Connection to MongoDB closed.")
  }
}


func main() {
AllPeople=[]Payment{
Payment{Id:1,MentorId:1,TrainingId:1,Txntype:"type1",Amount:"989",Datetime:"10:10:2020",Remarks:"nice"},
Payment{Id:2,MentorId:2,TrainingId:2,Txntype:"type2",Amount:"9892",Datetime:"12:12:2020",Remarks:"nice"}}
myRouter := mux.NewRouter().StrictSlash(true)
myRouter.HandleFunc("/",homelink)
myRouter.HandleFunc("/createpayment",createPayment).Methods("POST")
myRouter.HandleFunc("/getpayment/{id}",GetPayment)
myRouter.HandleFunc("/allpayment",AllPayment)
myRouter.HandleFunc("/updatepayment/{id}",updatePayment).Methods("PUT")
myRouter.HandleFunc("/deletepayment/{id}",deletePayment).Methods("DELETE")
http.ListenAndServe(":9826",myRouter)
}




