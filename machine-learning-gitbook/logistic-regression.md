## Linear Regression

Given input/output pairs $$x, r$$

Given $$x$$, predict $$r$$ **Least-squares** regression. Find line(linear function of $$x$$).

Least Squares Linear Regression finds the linear function 
$$
g(x) = w_1x + w_0
$$

that minimizes squares error on $$X$$.

Squared error of $$h$$ on $$X$$

$$
\frac{1}{N}\sum(r^t - h(x^t))^2
$$

# Logistic Regression

Logistic regression is a learning algorithm used in a supervised learning problem when the output $$y$$ are all either zero or one. The goal of logistic regression is to minimize the error between its predictions and training data.

## Eg. Cat vs No - cat

Given an image represented by a feature vector 𝑥, the algorithm will evaluate the probability of a cat being in that image.


$$
Given \ x, \hat{y} = P(y = 1 | x), where \  0 \leq \hat{y} \leq 1
$$


* The input features vector: $$x \in \mathbb{R}^{n_x}$$, where $$n_x$$ is the number of features
* The training label: $$ y \in 0,1$$
* The weights: $$w \in \mathbb{R}^{n_x}$$, where 𝑛𝑥 is the number of features
* The threshold: $$b \in \mathbb{R} $$
* The output: $$ \hat{y} = \sigma(wT\_x + b)
  $$
* Sigmoid function: $$s = \sigma(wT_x + b) = \sigma(z)= \frac{1}{1 + e^{-z}}$$

![](/assets/Snip20180126_8.png)

---

## Cost Function


$$
Given \ {(x^{(1)}, y^{(1)}), ..., (x^{(m)}, y^{(m)})}, we \ want \ \hat{y}^{(i)}   \approx y^{(i)}
$$
$$x^{(i)}$$ is $$i$$ th training example



### Loss\(error\) function:

The loss function measures the discrepancy between the prediction $$\hat{y}^{(i)}$$ and the desired output $${y}^{(i)}$$. In other words, the loss function computes the error for a single training example.

![](/assets/Snip20180127_10.png)

### Cost function

The cost function is the average of the loss function of the entire training set. we are going to find the parameters $$w$$ and $$b$$ that minimize the overall cost function![](/assets/Snip20180127_11.png)