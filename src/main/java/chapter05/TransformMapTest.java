package chapter05;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformMapTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //从元素中读取数据
        DataStreamSource<Event> stream = env.fromElements(new Event("Mary","./home",1000l),
                new Event("Bob","./cart",2000l),
                new Event("Alice","./prod?id",3000l))
        ;


        //进行转换计算
        SingleOutputStreamOperator<String> result = stream.map(new MyMapper());
        result.print();
        env.execute();



    }
    //自定义MapFunction
    public static class MyMapper implements MapFunction <Event,String>{

        public String map(Event event) throws Exception {
            return event.user;
        }

    }
}

