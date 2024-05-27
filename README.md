# News Article Analyser

This application analyses the given set of news articles against the given set of companies to
identify how many number of companies are mentioned in the articles.

### Configurations

`application.yml` file contains several configurations properties that can/ need to be set to run the application

| Configuration Name                       | Description                                                                                                                                                                |
|------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `analyser.path-to.company-list-csv-file` | Copy the csv file that contains the company info to a desired location <br/>and set the path to it here                                                                    |
| `analyser.path-to.article-list-dir`      | Copy the list of news article XML files to a desired directory and set <br/>the path to that directory here                                                                |
| `analyser.init.batch-size`               | This will determine the partition size to which the <br/>initial news article file names will be divided. Based on this, the files will be loaded in batches and processed |
| `analyser.csv.delimiter`                 | This will determine how to split the CSV file to its fields. <br/>Given that the provided CSV file used a ; as the separator                                               |

### Technologies used

- Java 21
- Spring Boot 3.2.6
- Lombok
- Instancio

### How to run

- Open terminal and navigate to the root directory of the project
- run `/.mvnw clean package`
- `cd target/`
- run `java -jar news-article-analyser-0.0.1-SNAPSHOT.jar`

Application will start reading the files from the given locations and start processing on start-up

### REST API

- Endpoint to get all the identified companies
`GET http://localhost:8080/api/v1/companies`
- Sample response

```json
[
    {
        "companyId": 22775,
        "companyName": "Facebook Inc"
    },
    {
        "companyId": 63318,
        "companyName": "Malaysia Airlines"
    },
    {
        "companyId": 1902,
        "companyName": "Bloomberg LP"
    },
    {
        "companyId": 2,
        "companyName": "ABB Ltd (ABB Asea Brown Boveri Ltd)"
    }
]
```

### Technical decisions and assumptions

- Minimum number of libraries and other technologies used to keep the solution clean and simple for the purpose of this assignment. 
Did not want to add unnecessary complexity to it.
- A ConcurrentHashMap was used to store the identified company objects instead of storing them in a database again to 
reduce complexity and keep the solution simple. A proper way forward would be to store them  in a database with corresponding article details as well.
Ex: This would be an improved data model
```json
[
  {
    "articleId": "1001",
    "articleName": "Sunday News Paper",
    "companiesMentioned": [
      {
        "companyId": "2000",
        "companyName": "Facebook Inc"
      },
      {
        "companyId": "2400",
        "companyName": "Instagram Inc"
      }
    ]
  }
]
```
- A message/ event broker like `RabbitMQ`, `Kafka` could have been used as an improvement to publish the contents of the identified companies 
so the database storing logic would be decoupled and can be carried out asynchronously
- Algorithm can be improved, given more time, to identified false positives
Ex: In the company list there is a company called `York`. This is matched to phrases like `New York` in the articles. 
which are not really company names.