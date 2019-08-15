import React from 'react';
import { 
    AppRegistry, 
    StyleSheet, 
    Text, 
    View,
    NativeModules,
    TouchableOpacity
} from 'react-native';


class HelloWorld extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            message: "Hello word!"
        }
    }

  render() {
    return (

        <View style={styles.container}>
            <TouchableOpacity 
                onPress={() => {
                    this.setState({
                        message: "Hello w!"
                    })
                    NativeModules.ToastForAndroid.show(1000)
                }}>
                    <Text style={styles.hello}>{this.state.message}</Text>
            </TouchableOpacity>
        </View>

    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    borderWidth: 1,
    borderColor: '#aaa',
    backgroundColor:'transparent'
  },
});
AppRegistry.registerComponent('RNHighScores', () => HelloWorld);