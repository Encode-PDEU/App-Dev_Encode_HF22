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
            height: 150,
            width: 200,
            margin: EdgeInsets.symmetric(horizontal: 30, vertical: 30),
            decoration: const BoxDecoration(
              color: Colors.blue,
              borderRadius: BorderRadius.all(Radius.circular(20)),
            ),
            padding: const EdgeInsets.all(20),
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
                  'Create tour Own Quiz',
                )),
                TextButton(
                  onPressed: () {},
                  child: Text('Test'),
                  style: TextButton.styleFrom(
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
