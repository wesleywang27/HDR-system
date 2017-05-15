import mnist_loader
from sklearn.externals import joblib
import time

print(time.strftime('%Y-%m-%d %H:%M:%S'))

training_data, validation_data, test_data = mnist_loader.load_data()
svc_clf = joblib.load('model/svc_clf.model')

predictions = [int(a) for a in svc_clf.predict(test_data[0])]
num_correct = sum(int(a == y) for a, y in zip(predictions, test_data[1]))
print("%s of %s test values correct." % (num_correct, len(test_data[1])))

print(time.strftime('%Y-%m-%d %H:%M:%S'))