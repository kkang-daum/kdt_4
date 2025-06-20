import 'package:flutter/material.dart';
import 'package:tripapp/models/trip_destination.dart';
import 'package:tripapp/screens/home/home_grid_item_widget.dart';

class HomeGridWidget extends StatelessWidget{
  List<TripDestination> destinations;
  HomeGridWidget(this.destinations);
  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          crossAxisSpacing: 12,
          mainAxisSpacing: 12,
          childAspectRatio: 1.1//가로 세로 비율.. 가로가 세로보다 1.1 배..
      ),
      itemCount: destinations.length,
      itemBuilder: (context, index){
        var destination = destinations[index];
        return GestureDetector(
          onTap: (){},
          child: HomeGridItem(destination),
        );
      },
    );
  }
}