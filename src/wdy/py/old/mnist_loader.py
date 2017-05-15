import _pickle as cPickle


def load_data():
    f = open('dataset/mnist.pkl', 'rb')
    training_data, validation_data, test_data = cPickle.load(f)
    f.close()
    return training_data, validation_data, test_data
