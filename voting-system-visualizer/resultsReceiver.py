import pika
import rabbitListener
import visualizer
import configReader


class ResultsReceiver:

    exchange_type = 'fanout'
    conf_file = '.conf'

    def __init__(self, votings_names, chart_type):
        self.votings_names = votings_names
        self.chart_type = chart_type

        self.channel = self.create_channel()
        self.consume_results()

    def consume_results(self):
        rl = rabbitListener.RabbitListener(self.channel,
                                           visualizer.Visualizer(self.votings_names, self.chart_type),
                                           self.conf_file)
        rl.start_consuming()

    def create_channel(self):
        config_reader = configReader.ConfigReader(self.conf_file)
        user_credentials = pika.credentials.PlainCredentials(config_reader.message_broker_user,
                                                             config_reader.message_broker_pwd)
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=config_reader.message_broker_ip,
                                      port=config_reader.message_broker_port,
                                      virtual_host=config_reader.message_broker_virtual_host,
                                      credentials=user_credentials)
        )
        channel = connection.channel()
        channel.exchange_declare(exchange=config_reader.exchange_name, exchange_type=self.exchange_type)
        return channel
