#include <iostream>
#include <string>
#include <vector>
#include "box.h"
using namespace std;

int main(){
	
	cout << "RICHARD HALE\nCS3424\nDecember 1st, 2010\nProject 'box'\n" << endl;
	box<int> intBox1(create(50,0));
	cout<<"The size of Box 1 is:\n"<<endl;
	cout << intBox1.size() << endl;
	cout<<"0 is found in Box 1 "<<intBox1.count(0)<< " times."<<endl;
	intBox1.emptyBox();
	cout<<"The size of Box 1 after emptyBox() function:"<<endl;
	cout << intBox1.size() << endl;

	box<int> intBox2;
	
	intBox2.populate();
	cout<<"\nBox 2 populated with the numbers 0 through 49" << endl;
	 intBox2.print();
	 intBox2.insertBox(55);
	 cout<<"Box 2 populated with the numbers 1 through 49 and 55 inserted at the end" << endl;
	intBox2.print();
	cout<<"The location of '5' in box 2 is at index:"<<endl;
	cout << intBox2.search(5) << endl;
	cout<<"Notice 5 has now been removed from the box after the search "<<endl;
		intBox2.print();
	cout << intBox2.search(50) << "\n50 is not found anywhere in the box."<< endl;

	
	box<int> intBox3;
	// intBox3.add(intBox1, intBox2);
	
	return 0;
}