import resultsReceiver


class RabbitListener():

    def __init__(self, channel, visualizer):
        self.channel = channel
        result = self.channel.queue_declare(queue='')
        queue_name = result.method.queue
        self.channel.queue_bind(queue=queue_name,
                                exchange=resultsReceiver.ResultsReceiver.exchangeName)
        self.channel.basic_consume(queue=queue_name,
                                   auto_ack=True,
                                   on_message_callback=self.callback)
        self.visualizer = visualizer

    def callback(self, ch, method, properties, body):
        if properties.type:
            print('TYPE: ', properties.type)
        print(type(properties.headers))
        if properties.headers:
            for key, value in properties.headers.items():
                print('key: ', key, 'value: ', str(value))
            self.visualizer.update_votes(properties.headers)

    def start_consuming(self):
        self.channel.start_consuming()

    def stop(self):
        self.channel.stop_consuming()
