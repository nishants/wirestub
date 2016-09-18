require "rspec/core/rake_task"


RSpec::Core::RakeTask.new do |task|
  task.pattern = Dir['src/test/e2e/**/*_spec.rb'].sort
end

RSpec::Core::RakeTask.new(:spec)

task :default => :spec