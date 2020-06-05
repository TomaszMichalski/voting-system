import string


class ConfigReader():

    def __init__(self, filename):
        self.filename = filename
        self.load_config()

    def load_config(self):
        file = open(self.filename, "r")
        self.message_broker_ip = self.load_message_broker_ip(file)
        self.message_broker_port = self.load_message_broker_port(file)
        self.message_broker_user = self.load_message_broker_user(file)
        self.message_broker_pwd = self.load_message_broker_pwd(file)
        self.message_broker_virtual_host = self.load_message_broker_virtual_host(file)
        self.exchange_name = self.load_exchange_name(file)
        file.close()

    def load_message_broker_ip(self, file):
        return self.load_conf_value(file, "messageBrokerIp:")

    def load_message_broker_port(self, file):
        return self.load_conf_value(file, "messageBrokerPort:")

    def load_message_broker_user(self, file):
        return self.load_conf_value(file, "messageBrokerUser:")

    def load_message_broker_pwd(self, file):
        return self.load_conf_value(file, "messageBrokerPwd:")

    def load_message_broker_virtual_host(self, file):
        return self.load_conf_value(file, "messageBrokerVirtualHost:")

    def load_exchange_name(self, file):
        return self.load_conf_value(file, "exchangeName:")

    def load_conf_value(self, file, beggining):
        for line in file:
            if line.startswith(beggining):
                non_whitespace_line = line.translate({ord(c): None for c in string.whitespace})
                return non_whitespace_line[len(beggining):]
        print('No ' + beggining + ' found in given conf file')
        return ''