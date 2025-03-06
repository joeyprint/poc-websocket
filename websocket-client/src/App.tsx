import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import useWebSocket from "react-use-websocket";

function App() {
  const websocketUrl = import.meta.env.VITE_WEB_SOCKET_URL;
  const helloSocketUrl = `${websocketUrl}/1/business-overview`;

  const { lastMessage, readyState } = useWebSocket(helloSocketUrl, {
    shouldReconnect: () => true,
  });

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>PoC WebSocket</h1>
      <div className="card">
        <p>
          <strong>Status:</strong>{" "}
          {readyState === 1 ? "Connected ✅" : "Disconnected ❌"}
        </p>
        <p>{lastMessage ? lastMessage.data : "Not have a message"}</p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default App;
