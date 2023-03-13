# proto-demo

# schema-proto git link 
#  mvn package 
# mvn install
https://github.com/quhaos/schema-proto.git

# py test git link 
https://github.com/quhaos/py-proto-test
# py生成pb文件
protoc -I=D:\code\py-proto-test\proto\p2 --python_out=D:\code\py-proto-test\proto\p2 D:\code\py-proto-test\proto\p2\Feedback.proto
protoc -I=D:\code\py-proto-test\proto\p3 --python_out=D:\code\py-proto-test\proto\p3 D:\code\py-proto-test\proto\p3\Feedback.proto


# found 
1. proto2 和 proto3 之间可以互相通信，并且可以正常解析，前提是P2设定的默认值和P3默认值相同。
2. proto 序列化时不会序列化默认的字段,反序列化字段值根据服务端设定的默认值设定.


#reference
https://protobuf.dev/
https://zhuanlan.zhihu.com/p/46603988
https://stackoverflow.com/questions/31801257/why-required-and-optional-is-removed-in-protocol-buffers-3
