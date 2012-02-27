#ifndef BOX
#define BOX

#include <iostream>
#include <string>
#include <vector>

using namespace std;

template<typename T>
class box {
	
public:	
	//empty box
	box();
	
	//box constructor
	box(vector<T> v){elements = v;}
	
	//function to create a box of size s and default elements T value
	std::vector<T> create(int s, T value);
		
	//Searches for an item, T item, in the box and returns the index of it if found. Otherwise, return -1
	T search(T item){
		for(unsigned i=elements.front();i<elements.size();i++)
			if(elements[i]==item){
				elements.erase(elements.begin()+i);
				return i;
			}
		return -1;
	}
	//return the size of the box
	int size()const {return (int) elements.size();}
	
	int count(T item){
		int c=0;
		for(unsigned i=elements.front();i<elements.size();i++)
			if(elements[i]==item)
				c++;
		return c;
	}

	//empties the box of all elements
	void emptyBox(){elements.erase(elements.begin(), elements.end());}
	
	//adds two vectors together sequentially into a new vector
	std::vector<T> add(vector<int> v1, vector<int> v2);
	
	
	void populate(){
		//if(elements.size()>0)
			for(unsigned i=0; i <50;i++)
				elements.push_back(i);

				}
	//This function prints every element in the box calling it
	void print(){
		for(unsigned i=elements.front();i<elements.size();i++)
			cout<<elements[i]<< endl;
		cout<<"\n"<<endl;
	}
	
	//insert an element, T something, into the back of the box
	void insertBox(T something){elements.push_back(something);}

private:
	vector<T>elements;

};
template <typename T>
box<T>::box(){}

//function to create a box of size s and default elements T value
template <typename T>
std::vector<T> create(int s, T value){
		vector<T> newBox(s, value);
		return newBox;
}
//adds two vectors together sequentially into a new vector
template <typename T>
std::vector<T> add(box<int> b1, box<int> b2){
		vector<T> sum((b1.size-1)+(v2.size-1));
		for(unsigned i=0;i < v1.size(); i++){
			sum.push_back(v1[i]);
		}
		for(unsigned i=0;i < v2.size(); i++){
			sum.push_back(v2[i]);
		}
		return sum;
	}



#endif