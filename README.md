# stan-seckill

这是一个用于学习应对高并发情况的秒杀系统
学习是一个过程，我们从最原始的方法构建一个秒杀系统，
然后再从一个一个的问题上解决问题，这样使得我们学习
更加深刻

## 1.0版本
 技术：springboot+mybatis
 1.0版本使用最原始的方式构建，所有业务都存在一个系统中
 使用@Transactional控制事务回滚 Synchroized关键字保证
 高并发安全
 测试：
 使用jmeter测试秒杀接口情况如下
 
 ![Image text](https://github.com/sidanchen/images/blob/master/seckill/test/seckill-test-1.jpg)
  
## 2.0版本
 技术：springboot+mybatis+redis+rabbitmq
 2.0版本新加入redis缓存技术以及rabbitmq消息中间件
 使用redis做缓存减轻对高并发时对数据库的压力，将
 系统拆分为生产者以及消费者，生产者将用户秒杀订单业务
 入队，消费者消费队列中的订单业务，减轻单台服务器以及
 数据库的压力
 
 2.0系统流程图
 ![Image text](https://github.com/sidanchen/images/blob/master/seckill/test/seckill-image-2.jpg)
  
 2.0测试结果图
 ![Image text](https://github.com/sidanchen/images/blob/master/seckill/test/seckill-test-2.jpg)
 