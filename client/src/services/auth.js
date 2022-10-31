// keep
const url = 'http://localhost:8080';

export async function authenticate(credentials) {
  const init = {
    method: 'POST',
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(credentials)
  };

  const response = await fetch(`${url}/authenticate`, init);
  if (response.status === 200) {
    const json = await response.json();
    console.log("Here???");
    console.log("Jason: ", json.jwt);
    setToken(json.jwt);
    return makeUser(json.jwt);
  } else if (response.status === 403) {
    console.log("Forbiden?");
    return ['Bad credentials'];
  } else {
    console.log("Or something else");
    return Promise.reject(['Something unexpected happened!']);
  }
};

export const refreshToken = async () => {  
  
  const token = getToken();
  if (!token) {
    return Promise.reject("Forbidden!")
  }

  const init = {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`
    }
  };

  const response = await fetch(`${url}/refresh_token`, init);
  if (response.status === 200) {
    const json = await response.json();
    setToken(json.jwt_token);
    return makeUser(json.jwt_token);
  } else if (response.status === 403) {
    return ['Bad credentials'];
  } else {
    return Promise.reject(['Something unexpected happened!']);
  }
};

export const registerUser = async (credentials) => {
  const init = {
    method: 'POST',
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(credentials)
  };

  const response = await fetch(`${url}/register`, init);
  if (response.status === 201 || response.status === 400) {
    return response.json();
  } else {
    return Promise.reject(['Something unexpected happened!']);
  }
};

export const logout = () => {
  localStorage.removeItem('jwt_token');
};

const makeUser = (jwtToken) => {
  const decodedToken = decodeToken(jwtToken);
  const userInfo = JSON.parse(decodedToken);

  return {
    username: userInfo.sub,
    roles: userInfo.authorities.split(','),
    userId: userInfo.app_user_id
  };
};

const decodeToken = (jwtToken) => {
  const tokenParts = jwtToken.split('.');
  if (tokenParts.length === 3) {
    console.log("Is this the user? Hilarious! ", atob(tokenParts[1]));
    return atob(tokenParts[1]);
  }
  console.error('Invalid token');
};

const setToken = (jwtToken) => {
  localStorage.setItem('jwt_token', jwtToken);
};

const getToken = () => {
  return localStorage.getItem('jwt_token');
}