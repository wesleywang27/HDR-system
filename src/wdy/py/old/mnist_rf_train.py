import mnist_loader

import time
from sklearn.externals import joblib
from sklearn.ensemble import RandomForestClassifier


def rf_baseline():
    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    training_data, validation_data, test_data = mnist_loader.load_data()
    rf_clf = RandomForestClassifier(n_estimators=400, n_jobs=4, verbose=1)
    rf_clf.fit(training_data[0], training_data[1])

    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    joblib.dump(rf_clf, 'model/rf_clf.model')

if __name__ == "__main__":
    rf_baseline()