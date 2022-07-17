package chapter05;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformFilterTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<Event> stream = env.fromElements(new Event("Mary","./home",1000l),
                new Event("Bob","./cart",2000l),
                new Event("Alice","./prod?id",3000l))
                ;
        //传入了一个实现了FilterFunction的类的对象
        SingleOutputStreamOperator<Event> filter = stream.filter(new MyFilter());
        //2.传入一个匿名类实现FilterFunction接口
        stream.filter(new FilterFunction<Event>() {
            public boolean filter(Event event) throws Exception {
                return event.user.equals("Mary");
            }
        });
        //3.传入Lambda表达式
        stream.filter(data->data.user.equals("Alice")).print("lambda: Alice click");
        filter.print();
        env.execute();

    }
    //实现一个自定义的FilterFunction
    public static class MyFilter implements FilterFunction<Event>{

        public boolean filter(Event event) throws Exception {
            return event.user.equals("Mary");
        }
    }
}
