import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.stereotype.Component;

@Component
public class Config {

    public AMQImpl.Queue commonQueue(){
        return null;
    }
}
