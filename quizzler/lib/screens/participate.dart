import 'package:flutter/material.dart';

class Participate extends StatelessWidget {
  const Participate({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Column(
          children: [
            Padding(
                padding: EdgeInsets.fromLTRB(8.0, 34.0, 8.0, 4.0),
                child: Stack(
                    alignment: Alignment.topLeft,
                    children: [
                      Image.asset('images/img.png'),
                      Container(
                        height: 50.0,
                        padding: EdgeInsets.fromLTRB(6.0, 5.0, 0, 0),
                        child: Stack(
                            children: [
                              Image.asset('images/img_1.png'),
                              Container(
                                  padding: EdgeInsets.symmetric(horizontal: 15.0, vertical: 10.0),
                                  child: Icon(
                                    Icons.arrow_back_ios,
                                    color: Color.fromRGBO(32, 61, 131, 1),
                                    size: 26.0,
                                  )
                              ),
                            ]

                        ),
                      )
                    ]
                )
            ),
            const Padding(
              padding: const EdgeInsets.fromLTRB(31.0, 0,0,0),
              child: Align(
                alignment: Alignment.centerLeft,
                child: Text(
                  'Freshers\' Quiz',
                  style: TextStyle(
                      fontSize: 32.0,
                      color: Colors.white,
                      fontWeight: FontWeight.w500
                  ),
                ),
              ),
            ),
            const Padding(
              padding: const EdgeInsets.fromLTRB(31.0, 10.0,0,0),
              child: Align(
                alignment: Alignment.centerLeft,
                child: Text(
                  'Description',
                  style: TextStyle(
                      fontSize: 20.0,
                      color: Colors.white,
                      fontWeight: FontWeight.w500
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(14.0,6.0,19.0,0),
              child: Container(
                height: 124.0,
                width: 330.0,
                decoration: BoxDecoration(
                    color: Colors.white,
                    border: Border.all(color: Color.fromRGBO(255, 255, 255, 1)),
                    borderRadius: BorderRadius.all(Radius.circular(14.0))
                ),
                child: Padding(
                  padding: EdgeInsets.all(12.0),
                  child: Text(
                    'Freshers\' Quiz serves as an introductory to \n our club events to the new batch of \n students, it helps us to easily demonstrate \n the working and conduction of events by the club',
                    style: TextStyle(
                      fontSize: 16.0,
                      fontWeight: FontWeight.w300,
                      color: Colors.black,
                    ),
                  ),
                ),
              ),
            ),
            const Padding(
              padding: const EdgeInsets.fromLTRB(31.0, 10.0,0,0),
              child: Align(
                alignment: Alignment.centerLeft,
                child: Text(
                  'Details',
                  style: TextStyle(
                      fontSize: 20.0,
                      color: Colors.white,
                      fontWeight: FontWeight.w500
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(14.0,6.0,19.0,0),
              child: Container(
                height: 124.0,
                width: 330.0,
                decoration: BoxDecoration(
                    color: Colors.white,
                    border: Border.all(color: Color.fromRGBO(255, 255, 255, 1)),
                    borderRadius: BorderRadius.all(Radius.circular(14.0))
                ),
                child: Padding(
                    padding: const EdgeInsets.all(12.0),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: const <Widget>[
                        Align(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            'Date: 17 October',
                            style: TextStyle(
                                fontWeight: FontWeight.w300,
                                fontSize: 16.0
                            ),
                          ),
                        ),
                        Align(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            'Time: 6:30 Onwards',
                            style: TextStyle(
                                fontWeight: FontWeight.w300,
                                fontSize: 16.0
                            ),
                          ),
                        ),
                        Align(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            'Venue: C002',
                            style: TextStyle(
                                fontWeight: FontWeight.w300,
                                fontSize: 16.0
                            ),
                          ),
                        ),
                        Align(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            'Topic: General',
                            style: TextStyle(
                                fontWeight: FontWeight.w300,
                                fontSize: 16.0
                            ),
                          ),
                        )
                      ],
                    )
                ),
              ),
            ),
            SizedBox(
              height: 10.0,
            ),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 4.0),
              child: ElevatedButton(
                onPressed: (){},
                child: Text(
                  'Register now',
                  style: TextStyle(
                      fontSize: 20.0,
                      color: Colors.black
                  ),
                ),
                style: ButtonStyle(
                  // backgroundColor: MaterialStateProperty<Color>(Color.fromRGBO(187, 207, 251, 1)),
                    shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                        RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20.0),
                        )
                    ),
                    backgroundColor: MaterialStatePropertyAll<Color>(Color.fromRGBO(187, 207, 251, 1)),
                    padding: MaterialStatePropertyAll<EdgeInsets>(EdgeInsets.symmetric( vertical: 14.0,horizontal: 100.0))
                ),
              ),
            )
          ],
        )
    );
  }
}
