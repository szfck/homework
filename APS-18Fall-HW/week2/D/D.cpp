#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
struct Node {
    int price, quality;
    Node(int p, int q) : price(p), quality(q) { }
    Node(){ }
    bool operator < (const Node& node) const {
        if (price != node.price) return price < node.price;
        return quality < node.quality;
    }
};
int main() {
    int n;
    while (cin >> n) {
        vector<Node> vec;
        for (int i = 0; i < n; i++) {
            int p, q;
            cin >> p >> q;
            vec.push_back(Node(p, q));
        }
        sort(vec.begin(), vec.end());
        bool flag = false;
        for (int i = 0; i < (int) vec.size() - 1; i++) {
            if (vec[i].quality > vec[i + 1].quality) {
                flag = true;
                break;
            }
        }
        cout << (flag ? "Happy Alex" : "Poor Alex") << endl;
    }
    return 0;
}
