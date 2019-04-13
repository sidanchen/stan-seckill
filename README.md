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
  