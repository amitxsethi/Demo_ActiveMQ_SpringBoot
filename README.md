This project is to demo working with activeMQ with springBoot
We require an instance of activeMQ to be running locally

Active MQ can be downloaded from https://activemq.apache.org/components/classic/download/

Once download and started. check that activeMQ is up and running by
check various commands available by ./activemq help
the most useful are
./activemq producer  : to generate test messages
./activemq dstat TEST : to check the destination "TEST" statastics

By default MQ broker would run at tcp://127.0.0.1:61616 but if it runs at different port, please modify this in the application.properties

There is a rest end point exposed to help publish a text message to the queue.
the end point can be accessed at
POST http://localhost:8080/sendMsg and body can contain any text message





