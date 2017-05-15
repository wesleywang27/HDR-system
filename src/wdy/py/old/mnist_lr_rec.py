import Image
from pylab import *
import numpy as np
from sklearn.externals import joblib
import os

count = sum([len(files) for root, dirs, files in os.walk('../../tmp/split/')])
lists = np.zeros((count, 784))

for num in range(0, count):
    img = Image.open('../../tmp/split/' + str(num) + '.jpg')
    img = img.resize((28, 28), Image.ANTIALIAS)
    arr = array(img.convert('L'), 'f')

    new_arr = []
    for row in arr:
        for i in row:
            if i < 50:
                i = 0
            else:
                i /= 256
            new_arr.append(i)
    new_arr = np.array(new_arr, dtype=np.float64, order="C", copy=True)
    lists[num] = new_arr

lr_clf = joblib.load('model/lr_clf.model')

predicted = lr_clf.predict(lists)

print(predicted)
