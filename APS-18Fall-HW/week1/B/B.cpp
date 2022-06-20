#include <iostream>
using namespace std;
int main() {
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int m;
        cin >> m;
        int x;
        int sum = 0;
        for (int j = 0; j < m; j++) {
            cin >> x;
            sum += x;
        }
        cout << sum << endl;

    }
    return 0;
}
