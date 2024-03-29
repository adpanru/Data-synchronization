## 项目概述

本项目是一个集成化的数据处理系统，利用RPC框架搭建，并结合了Xxl-job、Redis和Kafka等中间件。项目的核心目标是进行大规模数据的同步与校准，确保数据在A表和B表之间的一致性。

## 项目目标

此项目的核心目标是解决由于服务器宕机或网络波动导致的数据不匹配问题。通过定期的数据校准，确保A表中的数据与B表中的数据保持一致。

## 架构设计

系统整体架构分为四个主要服务：a服务、b服务、c服务和d服务。

1. **a服务**：数据源提供者，负责提供数据。
2. **b服务**：采用Xxl-job作为任务调度工具，定期从a服务获取数据。为确保数据的完整性和可用性，b服务可部署多个实例以分散负载。从a服务获取的数据被发送至Kafka，同时将b表中的数据存储在Redis中。
3. **c服务**：作为Kafka的消费者，处理从Kafka中获取的数据。c服务会与Redis中的数据进行对比，将发生变化的数据（新增、删除、修改）分别存入Redis的特定队列中（insert、delete、update）。
4. **d服务**：从Redis的队列中读取数据，进行更新操作。d服务可以部署多个实例以形成集群，提高数据处理能力。

此外，项目还利用了分布式锁和事务保证数据的一致性和完整性。通过使用Redis和Kafka，确保了数据处理的分布式特性和高可用性。

## 技术栈

- **RPC框架**：提供远程过程调用功能，实现服务的解耦和分布式部署。
- **Xxl-job**：任务调度平台，用于定时执行数据同步任务。
- **Redis**：高性能的内存数据库，用于存储临时数据和实现分布式锁。
- **Kafka**：分布式消息队列，用于数据的异步传输和处理。
