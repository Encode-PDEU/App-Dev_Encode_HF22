import 'package:flutter/material.dart';
// import 'package:quotes_app/screens/second_screen.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String quote = "";
  String author = "";

  // @override
  // void initState() {
  //   super.initState();
  // }
  //
  // @override
  // void dispose() {
  //   super.dispose();
  // }

  @override
  Widget build(BuildContext context) {
    String? formattedAuthor;
    if (quote.length == 0)
      formattedAuthor = "";
    else
      formattedAuthor = "~ $author";

    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          title: Center(child: Text("QuotiFy")),
        ),
        body: Column(
          children: [
            SizedBox(
              height: MediaQuery.of(context).size.height / (3),
            ),
            Padding(
              padding: const EdgeInsets.all(10.0),
              child: Center(
                child: Text(
                  quote,
                  style: TextStyle(fontSize: 20),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
            Align(
                alignment: Alignment.bottomRight,
                child: Padding(
                  padding: const EdgeInsets.only(right: 10.0),
                  child: Text(
                    formattedAuthor,
                    style: TextStyle(fontSize: 18),
                  ),
                )),
            Spacer(),
            Align(
              alignment: Alignment.bottomCenter,
              child: ElevatedButton(
                onPressed: () async {
                  var url = Uri.parse(
                      "https://goquotes-api.herokuapp.com/api/v1/random?count=1");
                  var response = await http.get(url);
                  print("Response status: ${response.statusCode}");
                  print("Response body: ${response.body}");
                  var data = jsonDecode(response.body);

                  quote = data["quotes"][0]["text"];
                  author = data["quotes"][0]["author"];

                  setState(() {});

                  // Navigator.of(context).push(
                  //     MaterialPageRoute(
                  //         builder: (context) => SecondScreen(quoteText: quote, authorName: author)
                  //     )
                  // );
                },
                child: Text("Get a new quote"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
