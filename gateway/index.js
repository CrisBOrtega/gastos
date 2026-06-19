import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import { ApolloGateway, IntrospectAndCompose } from '@apollo/gateway';

const gateway = new ApolloGateway({
  supergraphSdl: new IntrospectAndCompose({
    subgraphs: [
      { name: 'prototype', url: 'http://localhost:8080/graphql' },
      { name: 'ur', url: 'http://localhost:8081/graphql' },
    ],
  }),
});

const server = new ApolloServer({
  gateway,
});

try {
  const { url } = await startStandaloneServer(server, {
    port: 4000,
  });
  console.log(`🚀 Gateway ready at ${url}`);
} catch (error) {
  console.error('Failed to start Apollo Gateway:', error);
}
