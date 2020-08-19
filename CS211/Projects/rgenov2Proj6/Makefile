proj6Header: proj6Base.o Island.o
	g++ -o proj6Header proj6Base.o Island.o

proj6Base.o: proj6Base.cpp proj6Header.h
	g++ -c proj6Base.cpp

Island.o: Island.cpp proj6Header.h
	g++ -c Island.cpp
al