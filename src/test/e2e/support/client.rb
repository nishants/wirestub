require 'http' # URI is required by Net::HTTP by default
require 'json' # URI is required by Net::HTTP by default

module Jeyson
  class Client
    def initialize(root, port, default_headers = {})
      @root     = root
      @port     = port
      @headers  = default_headers
      @remote   = Net::HTTP.new("http://localhost",5132)
    end

    def get(request_url, headers = {'Content-Type' => "application/json"})
      response = HTTP.headers({'Content-Type' => "application/json"}).get(url(request_url))
      JSON.parse(response.body.to_s)
    end

    def url(url)
      "#{@root}#{url}"
    end

  end
end