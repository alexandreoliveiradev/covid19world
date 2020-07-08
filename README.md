# Hello 
## and welcome to Covid19World

My name is Alexandre Oliveira and I'm creating a program that gets data from [worldometers](www.worldometers.info)
regarding Covid_19, and presents it in a condensed way and let's you easily search for info from every 
country or region on the Planet, according that they need to be available on worldometers.
 
This is a small(just for fun) project that I'm using to learn new stuff. 

Hope you can use this application, that is going to be under development in the incoming times.

Cheers, 
Alex

*I need to thank my friend Marta Pinheiro who helped me out in the process.*

### Download link:
Get Covid19World [here](https://mega.nz/file/Nrw3yL7K#lJdd0l9akoiZaFjIf_wT5WH16rhrRl-6ca4TJVdjd6E).

#### Pre-requisites: 
Java installed on your computer.

Last update on June 22nd, 2020. If any trouble arises after this, its because worldometers updated their website.

### current version

v1.5 Last updated version, full data fetched, bugs fixed.

### console interface

This is how you can view compared countries:
![compare](https://imgur.com/dvmPH50)

This is the appearance of top countries:
![tops](https://imgur.com/1DHBXby)

This is the world report, in full text:
![report](https://imgur.com/eoATTjo)

And how a country report looks like:
![report2](https://imgur.com/nEngNUA)


### older updates:

v1.4 Now you can download the executable file [here](https://mega.nz/file/Nrw3yL7K#lJdd0l9akoiZaFjIf_wT5WH16rhrRl-6ca4TJVdjd6E) or just run the .jar on target/artifacts with `$java -jar covid19world.jar`. 
Includes new Recovered and Reload Option, fixed bugs.
Features now include: *Graphs, Compare Countries, Country Reports, Tops and Helpful Info*.

v1.3 At this point you can just run the .jar on target/artifacts with `$java -jar covid19world.jar` and you have 
various options and a lot of ways to view data. It's no longer a Spring Boot Application.

v1.2
The program now works on your console. Just run the Application and use only the characters asked for.

v.1.1
Now the project is a ~~Spring Boot Application~~, so you can run the utils.Application and then open your browser
or any browser simulation service(Postman) and type on the Url: 
"http://localhost:8080/relatorio?country=NAMEOFCOUNTRY" or http://localhost:8080/relatorio?country=world for a full world
report.

In it's v1.0 it's all in Portuguese, which will be replaced as we go, and it's applications can be found
in the domain.WorldCasesTest, where you can request a full world report, or a country one. 

