#include <iostream>
using namespace std;
int main() {
    int n;
    while (cin >> n) {
        if (n == 0) break;
        int sum = 0;
        int x;
        for (int i = 0; i < n; i++) {
            cin >> x;
            sum += x;
        }
        cout << sum << endl;
    }
    return 0;
}
