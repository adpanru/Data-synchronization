## 该项目为spring cloud项目，集成了Xxl-job，redis，kafka
## 项目目的 a表中的1000w条数据，发生了改变，现在用将这些数据定期同步到b表
##架构设计 分为a b c d 四个服务
	a服务负责提供数据
	b服务利用Xxl-job 定期拿到a的数据，并且可以多开几个b服务去拿取a的数据，利用Xxl-job获取当前机器的总数量，和当前的机器数量
	然后去根据总机器数量取模拿到当前机器所需的数据，并将这些数据扔进kafka中，并将b表中的数据全部扔到redis中
	
	C服务作为kafka消费者集群，拿到kafka中的数据，并和redis中的数据进行比较，将发生变化的数据放入redis的队列中
		插入的数据存入redis的insert中，删除的数据存入redis的delete中，修改的数据存入redis的update中
	D服务进行读取redis中的队列，同样可以搭建集群，多台机器同时去从redis中拿取数据，并更新到redis中
	