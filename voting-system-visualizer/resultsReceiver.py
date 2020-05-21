import pika
import RabbitListener
import pieVisualizer


class ResultsReceiver:

    messageBrokerIp = '127.0.0.1'
    messageBrokerUser = 'bunny'
    messageBrokerPwd = 'bunny1234'
    messageBrokerVirtualHost = 'bunny_host'
    exchangeName = 'bunny_exchange'
    exchangeType = 'fanout'


    def __init__(self, votings_names, chart_type):
        self.votings_names = votings_names
        self.chart_type = chart_type

        self.channel = self.create_channel()
        self.consume_results()

    def consume_results(self):
        rl = RabbitListener.RabbitListener(self.channel,
                                           pieVisualizer.PieVisualizer(self.votings_names, self.chart_type))
        rl.start_consuming()

    def create_channel(self):
        user_credentials = pika.credentials.PlainCredentials(self.messageBrokerUser, self.messageBrokerPwd)
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=self.messageBrokerIp,
                                      virtual_host=self.messageBrokerVirtualHost,
                                      credentials=user_credentials)
        )
        channel = connection.channel()
        channel.exchange_declare(exchange=self.exchangeName, exchange_type=self.exchangeType)
        return channel
