#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n = 0, m = 0, input=0;
int arr[8] = { 0, };
int result[8] = { 0, };
bool visited[8] = {false,};

void recursive(int cnt) {
	
	int flag = 0;

	if (cnt == m) {
		for (int i = 0; i < m; i++) {
			cout << result[i] << "\n";
		}
	}
	for (int i = 0; i < n; i++) {
		if (arr[i] != flag && !visited[i]) {
			visited[i] = true;
			result[cnt] = arr[i];
			flag = result[cnt];
			recursive(cnt + 1);
			visited[i] = false;
		}
	}
}

int main() {

	cin >> n >>m;

	for (int i = 0; i < n; i++) {
		cin >> arr[i];

	}
	sort(arr, arr+n);
	recursive(0);

	return 0;
}