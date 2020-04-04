from typing import List, Tuple

import tensorflow as tf
import numpy as np
import os


class Scanner:

    def __init__(self):
        self.model = tf.keras.models.Sequential([
            tf.keras.layers.Conv2D(8, 7, activation=tf.nn.relu, input_shape=(156, 156, 3)),
            tf.keras.layers.MaxPool2D((3, 3)),
            tf.keras.layers.Conv2D(16, 5, activation=tf.nn.relu),
            tf.keras.layers.MaxPool2D((3, 3)),
            tf.keras.layers.Conv2D(32, 3),
            tf.keras.layers.Conv2D(32, 3),
            tf.keras.layers.Conv2D(16, 3),
            tf.keras.layers.MaxPool2D(3, 3),
            tf.keras.layers.Flatten(),
            tf.keras.layers.Dense(256),
            tf.keras.layers.Dense(2 + 10 + 10 + 1)
        ])

        self.model.compile('adam', loss=Scanner.loss, metrics=['accuracy'])

    @staticmethod
    def loss(y, y_hat):
        currency_hat, value_hat, preservation_hat, rotation_hat = tf.split(y_hat, [2, 10, 10, 1])
        currency, value, preservation, rotation = tf.split(y, [2, 10, 10, 1])

        loss_fn = tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True)
        loss = loss_fn(currency, currency_hat) + loss_fn(value, value_hat) + loss_fn(preservation, preservation_hat) + \
               loss_fn(rotation, rotation_hat)

        return tf.reduce_mean(loss)

    def train(self, epochs: int):
        self.model.fit(Scanner.load_data('data', True), epochs)

    @staticmethod
    def load_data(dirname: str, train: bool) -> tf.data.Dataset:
        dataset = tf.data.Dataset.from_tensor_slices(Scanner.read_files(dirname))
        dataset = dataset.map(Scanner.load_file)

        if train:
            dataset.cache()

        dataset = dataset.batch(50)
        dataset = dataset.prefetch(50)

        return dataset

    @staticmethod
    def read_files(dirname: str) -> Tuple[List[str], np.ndarray]:
        files = ([], np.nan)
        dirname_path = os.path.join(os.curdir, dirname)
        for currency in os.listdir(dirname_path):
            currency_path = os.path.join(dirname_path, currency)
            for value in os.listdir(currency_path):
                value_path = os.path.join(currency_path, value)
                for preservation in os.listdir(value_path):
                    preservation_path = os.path.join(value_path, preservation)
                    for file in os.listdir(preservation_path):
                        file_path = os.path.join(preservation_path, file)
                        print(f"{currency}, {value}, {preservation}, {file}")
        return files

    @staticmethod
    def load_file(file: str, label: np.ndarray) -> Tuple[str, np.ndarray]:
        img = tf.io.read_file(file)
        img = tf.image.decode_image(img)
        img = tf.image.resize(img, [128, 128])
        img /= 255
        return img, label


if __name__ == '__main__':
    scanner = Scanner()
    scanner.train(10)
