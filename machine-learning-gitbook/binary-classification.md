# Binary Classification

In a binary classification problem, the result is a discrete value output.

## Eg. Cat vs Non-cat

An image is store in the computer in 3 separate matrices corresponding to the Red, Green and Blue color channels of the image. The 3 matrices have the same size as the image, for example, the resolution of the image is 64 pixels $$\times$$ 64 pixels, the 3 matrices \(RGB\) are $$64 \times 64$$ each.

To create a feature vector, $$x$$, the pixel in intensity values will be **unroll** or **reshape** for each color. The dimension of the input feature vector $$x$$ is $$n_x = 64 \times 64 \times 3$$

![](/assets/Snip20180125_5.png) 

![](/assets/Snip20180125_7.png) 


