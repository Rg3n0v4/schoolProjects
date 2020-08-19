restaurantHeader: linkedListRestaurant.o linkedListInteraction.o userRestaurantDriver.o
	gcc -o restaurantHeader linkedListRestaurant.o linkedListInteraction.o userRestaurantDriver.o

linkedListRestaurant.o: linkedListRestaurant.c restaurantHeader.h
	gcc -c linkedListRestaurant.c

linkedListInteraction.o: linkedListInteraction.c restaurantHeader.h
	gcc -c linkedListInteraction.c

userRestaurantDriver.o: userRestaurantDriver.c restaurantHeader.h
	gcc -c userRestaurantDriver.c