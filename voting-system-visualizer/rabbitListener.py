import configReader


class RabbitListener():

    def __init__(self, channel, visualizer, conf_file):
        self.channel = channel
        result = self.channel.queue_declare(queue='')
        queue_name = result.method.queue
        self.channel.queue_bind(queue=queue_name,
                                exchange=configReader.ConfigReader(conf_file).exchange_name)
        self.channel.basic_consume(queue=queue_name,
                                   auto_ack=True,
                                   on_message_callback=self.callback)
        self.visualizer = visualizer

    def callback(self, ch, method, properties, body):
        if properties.headers:
            self.visualizer.update_votes(properties.type, properties.headers)

    def start_consuming(self):
        self.channel.start_consuming()

    def stop(self):
        self.channel.stop_consuming()
