# Implement the Backpropagation algorithm for a multilayer neural network (with one hidden layer) to classify numerical data

# Import the necessary libraries
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score


# Define the sigmoid function
def sigmoid(x):
    return 1 / (1 + np.exp(-x))


# Define the derivative of the sigmoid function
def sigmoid_derivative(x):
    return x * (1 - x)


# Define the input data
data = pd.read_csv("data.csv")

# Define the input and output features
X = data.drop("class", axis=1)
y = data["class"]

# Split the data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Scale the data
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# Define the number of input features, hidden units, and output units
input_features = X_train.shape[1]
hidden_units = 3
output_units = 1

# Initialize the model parameters
weights_input_hidden = np.random.uniform(size=(input_features, hidden_units))
bias_input_hidden = np.zeros(hidden_units)
weights_hidden_output = np.random.uniform(size=(hidden_units, output_units))
bias_hidden_output = np.zeros(output_units)

# Define the number of epochs and learning rate
epochs = 1000
learning_rate = 0.1

# Train the model
for epoch in range(epochs):
    # Feedforward pass
    hidden_layer_input = np.dot(X_train, weights_input_hidden) + bias_input_hidden
    hidden_layer_activations = sigmoid(hidden_layer_input)
    output_layer_input = np.dot(hidden_layer_activations, weights_hidden_output) + bias_hidden_output
    output = sigmoid(output_layer_input)

    # Backpropagation
    error = y_train - output
    d_output = error * sigmoid_derivative(output)
    error_hidden_layer = d_output.dot(weights_hidden_output.T)
    d_hidden_layer = error_hidden_layer * sigmoid_derivative(hidden_layer_activations)
    weights_hidden_output += hidden_layer_activations.T.dot(d_output) * learning_rate
    bias_hidden_output += np.sum(d_output, axis=0, keepdims=True) * learning_rate
    weights_input_hidden += X_train.T.dot(d_hidden_layer) * learning_rate
    bias_input_hidden
