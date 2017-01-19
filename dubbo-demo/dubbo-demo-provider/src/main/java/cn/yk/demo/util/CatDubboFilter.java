package cn.yk.demo.util;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.dianping.cat.message.Transaction;

public class CatDubboFilter implements Filter{

	@Override
    public Result invoke(Invoker<?> invoker, Invocation invocation)
            throws RpcException {

        Result result = null;
        String loggerName = invoker.getInterface().getSimpleName()+"."+invocation.getMethodName();
        Transaction t = Cat.newTransaction("service",loggerName);
        
        CatDubboContext catContext = new CatDubboContext();
        catContext.addProperty(Context.ROOT, invocation.getAttachment(Context.ROOT));
        catContext.addProperty(Context.PARENT, invocation.getAttachment(Context.PARENT));
        catContext.addProperty(Context.CHILD, invocation.getAttachment(Context.CHILD));
        Cat.logRemoteCallServer(catContext);
        
        try {
            result = invoker.invoke(invocation);
            t.setStatus(Transaction.SUCCESS);
        } catch(Exception e) {
            t.setStatus("ERROR");
            Cat.logError(e);
        } finally {
            t.complete();
        }

        return result;
    }


}
