# Spring Boot Messaging with RabbitMQ
<p align="center">
  <a href="#">
    <img alt="Techs" title="Techs" src="https://user-images.githubusercontent.com/34090058/116822958-5c75e980-ab8a-11eb-92f7-d9a3ac5f1516.png"width="700">
  </a>
</p>
### How can i run RabbitMQ ?

```
$  docker-compose -f docker-compose.yml up -d
```

### How does it work?
- There are 3 examples within the project,
Exchange Types
1) Direct, 
2) Topic
3) Fanout

> Therefore, I've created 2 projects that consumer and rabbitmq,
You can see that how can we handle rabbitmq produce/consume process,
Consumer Example:  only to see that how does fanout work,
