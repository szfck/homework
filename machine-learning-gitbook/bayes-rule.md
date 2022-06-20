## Bayes Rule

$$
p(c | x) = \frac{p(c)p(x | c)}{p(x)}
$$

### MAP Hypothesis 

In general, to predict the class given example x, we will choose the class with **highest** value for $$p(c | x)$$, this class is called **MAP hypothesis**.

### Maximum Likelyhood

Maximum likelyhood is class with **highest** value for $$p(x | c)$$.

---

### Naive Bayes Algorithm (classification)

Assume 
- $$n$$ binary attributes $$a_1, a_2 ... a_n$$
- finite set of classes $$V$$

Given example $$(a_1, a_2 ... a_n)$$

Find the "most probable" class label for $$a_1, a_2 ... a_n$$

$$V_{map} = argmax_{v_j \in V}\  P(v_j| a_1, a_2 ... a_n)$$

$$V_{map} = argmax_{v_j \in V}\  P(a_1, a_2 ... a_n | v_j) P(v_j)$$

$$V_{map} = argmax_{v_j \in V}\  P(a_1| v_j)P(a_2| v_j)...P(a_n| v_j) P(v_j)$$
###naive bayes assumption

attributes are independent in class

$$V_{map} = argmax_{v_j \in V}\  P(a_1| v_j)P(a_2| v_j)...P(a_n| v_j) P(v_j)$$

But frequency estimation may result in **0**. 
Using **add-1** smoothing 

 e.g. 
 
  R R G R R R G [R G B]
  
### train and test

**separate** the dataset to 70% training set and 30% test set.

Otherwise may **overly optimized** or **overfitting**

- The learning algorithm tries **"too hard"** to do well on training set **won't do well on future example**

- If error on test set is much higher than error on training set that **indicates overfitting**

For small datasets, instead of 1 train/set split, use **cross validation**

for each $$j$$, train on parts $${1, 2..5 - j}$$, test on $${j}$$
  


