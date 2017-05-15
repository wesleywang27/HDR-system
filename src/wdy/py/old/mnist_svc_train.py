import mnist_loader

import time
from sklearn.externals import joblib
from sklearn.svm import NuSVC


def svc_baseline():
    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    training_data, validation_data, test_data = mnist_loader.load_data()
    svc_clf = NuSVC(nu=0.02, kernel='rbf', gamma=0.02)
    svc_clf.fit(training_data[0], training_data[1])

    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    joblib.dump(svc_clf, 'model/svc_clf.model')

if __name__ == "__main__":
    svc_baseline()