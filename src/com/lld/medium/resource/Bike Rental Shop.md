
---

**Bike Rental Shop**

*Overview:*
You've been hired by a bike shop to create software to help them with their rental operation. This includes automating activities such as keeping track of inventory, customers, stock items that are rented, customer fee accrual, etc. The store rents two types of products: Bikes and Scooters. Each bike is made in one of three different sizes, Small, Medium, Large, and will fit small, medium, and large humans respectively. We’d like to be sure our customers get a bike that fits them and will need to track size of our bikes. The scooters are available in electric motor or gas motor styles. The styles of scooters are very different so we want to track them separately so we can match our customers' needs to the appropriate scooter.

*Required Queries:*
1. How many small bikes do you have?
2. What products are there for rent?
3. Does this customer have a balance? (aka owe us money)
4. What products are rented?
5. Are there products that are overdue for return? Who has them?
6. What products has a customer rented?

*Required Commands:*
1. Add a product to inventory
2. Add a customer
3. Remove a product from inventory (permanently not for rental, i.e., it’s been damaged)
4. Record that a product is rented to a customer
5. Create a charge for the customer

*Deliverables:*
- API spec
- Class diagrams
- Schema design
- Sequence diagram

---


Sure! Here is an example of how you can create an API spec, class diagrams, schema design, and sequence diagram using PlantUML.

### API Spec
We'll define some basic API endpoints for the required commands and queries.

```yaml
# API Spec
GET /bikes?size=small
GET /products
GET /customer/{customerId}/balance
GET /rented-products
GET /overdue-products
GET /customer/{customerId}/rented-products

POST /products
POST /customers
DELETE /products/{productId}
POST /rentals
POST /charges
```

### Class Diagrams

```plantuml
@startuml
class Product {
  +int id
  +String type
  +String size
  +boolean isRented
}

class Bike {
  +String size
}

class Scooter {
  +String motorType
}

class Customer {
  +int id
  +String name
  +String email
}

class Rental {
  +int id
  +int customerId
  +int productId
  +Date rentalDate
  +Date returnDate
}

class Charge {
  +int id
  +int customerId
  +double amount
  +String description
}

Product <|-- Bike
Product <|-- Scooter
Customer "1" --> "0..*" Rental
Customer "1" --> "0..*" Charge
Product "1" --> "0..*" Rental
@enduml
```

### Schema Design

```plantuml
@startuml
entity Product {
  * id : int
  * type : String
  * size : String
  * isRented : boolean
}

entity Bike {
  * productId : int
  * size : String
}

entity Scooter {
  * productId : int
  * motorType : String
}

entity Customer {
  * id : int
  * name : String
  * email : String
}

entity Rental {
  * id : int
  * customerId : int
  * productId : int
  * rentalDate : Date
  * returnDate : Date
}

entity Charge {
  * id : int
  * customerId : int
  * amount : double
  * description : String
}

Product ||--|{ Bike : has
Product ||--|{ Scooter : has
Customer ||--o{ Rental : makes
Customer ||--o{ Charge : incurs
Product ||--o{ Rental : is
@enduml
```

### Sequence Diagram

```plantuml
@startuml
actor User
participant "Product Service" as PS
participant "Customer Service" as CS
participant "Rental Service" as RS
participant "Charge Service" as CHS

User -> PS : GET /bikes?size=small
PS -> PS : Fetch small bikes

User -> PS : POST /products
PS -> PS : Add product to inventory

User -> CS : POST /customers
CS -> CS : Add customer

User -> PS : DELETE /products/{productId}
PS -> PS : Remove product from inventory

User -> RS : POST /rentals
RS -> RS : Record rental

User -> CHS : POST /charges
CHS -> CHS : Create charge

User -> CS : GET /customer/{customerId}/balance
CS -> CHS : Fetch customer charges
CHS -> CS : Return charges
CS -> User : Return balance

User -> RS : GET /overdue-products
RS -> RS : Fetch overdue products

User -> RS : GET /customer/{customerId}/rented-products
RS -> RS : Fetch customer rentals
@enduml
```

These diagrams and specs represent a high-level design for the bike rental shop software system. Each part can be further detailed and adjusted as needed based on specific requirements and implementation details.

