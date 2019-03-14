1.这里是基本的CURD操作，使用longguo的generator 生成可以分页的bean example mapper xml等。
2.主要对mapper的操作：增删改  ==》insert或者换成update 存在则修改 不存在insert  
            查单一对象  get**Info 统一用example查询
            查分页list列表   query**InfoList  用example BaseModel 进行分页处理（先查询count总数，然后处理）
            
3.全部都抽象出来了，如果有机会可以自己做一个generator 

4. 统一vo的request 做参数校验  参数校验返回结果使用常量（ValidateRequest）           
   