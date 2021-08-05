import React from 'react';
import logo from './logo.svg';
import { Counter } from './features/counter/Counter';
import {BrowserRouter, Route} from "react-router-dom";

import './App.scss';
import {Persist} from "./Persist/Persist";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <Route exact path={"/"}>
              <Persist/>
          </Route>
      </BrowserRouter>
    </div>
  );
}

export default App;
