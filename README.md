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
