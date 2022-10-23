import 'package:flutter/material.dart';

class QuizPages extends StatefulWidget {
  const QuizPages({Key? key}) : super(key: key);

  @override
  State<QuizPages> createState() => _QuizPagesState();
}

class _QuizPagesState extends State<QuizPages> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.indigo,
      appBar: AppBar(
        backgroundColor: Colors.indigo,
        title: const Text(
          'Quizzing!',
          style: TextStyle(
            fontSize: 40,
          ),
        ),
      ),
      body: ListView(
        children: [
          Container(
            height: 170,
            width: 200,
            margin: EdgeInsets.symmetric(horizontal: 40, vertical: 30),
            decoration: BoxDecoration(
              color: Colors.lightBlue.shade100,
              borderRadius: BorderRadius.all(Radius.circular(20)),
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Container(
                  child: const Text(
                    'Create a Quiz',
                    style: TextStyle(
                      fontSize: 25,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Container(
                  child: const Text(
                      'Create your own Quiz \n Play with your friends',
                      style: TextStyle(
                        fontSize: 15,
                      )),
                ),
                ElevatedButton(
                  onPressed: () {},
                  child: Text('Create'),
                  style: ElevatedButton.styleFrom(
                    shape: StadiumBorder(),
                    minimumSize: Size(110, 30),
                    maximumSize: Size(120, 70),
                    backgroundColor: Colors.white,
                    foregroundColor: Colors.black,
                  ),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
