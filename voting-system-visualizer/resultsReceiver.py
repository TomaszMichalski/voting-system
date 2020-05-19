import pika
import RabbitListener
import pieVisualizer


class ResultsReceiver:

    messageBrokerIp = '127.0.0.1'
    messageBrokerUser = 'bunny'
    messageBrokerPwd = 'bunny1234'
    messageBrokerVirtualHost = 'bunny_host'
    exchangeName = 'bunny_exchange'

    def main(self):
        print('[ResultsReceiver] starting ...')
        channel = self.create_channel()
        print('[ResultsReceiver] connection and channel created.')
        self.loop_user_input(channel)

    def loop_user_input(self, channel):
        rl = RabbitListener.RabbitListener(channel, pieVisualizer.PieVisualizer({}))
        rl.start_consuming()

    def create_channel(self):
        user_credentials = pika.credentials.PlainCredentials(self.messageBrokerUser, self.messageBrokerPwd)
        self.connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=self.messageBrokerIp,
                                      virtual_host=self.messageBrokerVirtualHost,
                                      credentials=user_credentials)
        )
        channel = self.connection.channel()
        channel.exchange_declare(exchange=self.exchangeName, exchange_type='fanout')
        return channel


if __name__ == '__main__':
    ResultsReceiver().main()