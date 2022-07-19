package chapter05;

import org.apache.flink.api.common.functions.AbstractRichFunction;
import org.apache.flink.api.common.functions.RichFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformRichFunctionTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<Event> stream = env.fromElements(new Event("Mary","./home",1000l),
                new Event("Bob","./cart",2000l),
                new Event("Alice","./prod?id",3000l));
                stream.map(new MyRichMapper());
    }
        //实现一个自定义的富函数
    public static class MyRichMapper extends RichMapFunction<Event,Integer>{
            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
            }

            @Override
            public Integer map(Event event) throws Exception {
                return null;
            }

            @Override
            public void close() throws Exception {
                super.close();
            }
        }


}
