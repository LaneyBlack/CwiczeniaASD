cmake_minimum_required(VERSION 3.22)
project(ASD)

set(CMAKE_CXX_STANDARD 23)

set(CMAKE_EXE_LINKER_FLAGS ${CMAKE_EXE_LINKER_FLAGS} "-static")

add_executable(prog1 prog1.cpp)
